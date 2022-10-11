package com.web.MyPetForApp.member.service;

import com.web.MyPetForApp.exception.BusinessLogicException;
import com.web.MyPetForApp.exception.ExceptionCode;
import com.web.MyPetForApp.image.service.ImageService;
import com.web.MyPetForApp.member.entity.Member;
import com.web.MyPetForApp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ImageService imageService;

    private final String baseMemberImgUrl = "https://mypet-image.s3.ap-northeast-2.amazonaws.com/members/";
    public Member create(Member member, List<MultipartFile> multipartFiles) {
    Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());

    Member savedMember = null;

    if(optionalMember.isPresent()) {
        throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    } else {
//        member.updateRole(Member.MemberRole.ROLE_ADMIN);
         savedMember = memberRepository.save(member);
        }
        String profileImg = null;

        if(multipartFiles != null) {
            profileImg  = baseMemberImgUrl + imageService.uploadFile(multipartFiles, "member", member.getMemberId(), "profile").get(0);
        }
        savedMember.updateProfileImg(profileImg);
        return savedMember;
    }

    public Member update(Member requestMember, List<MultipartFile> multipartFiles) {
        // 회원이 존재하는지 확인
        Member findMember = findVerifiedMember(requestMember.getMemberId());
        // 회원이 존재하고,
        String profileImg = null;
        // 바꿀 이미지가 존재한다면 포함하여 회원 정보를 수정한다.
        if(multipartFiles != null) {
            profileImg = baseMemberImgUrl + imageService.uploadFile(multipartFiles, "member", findMember.getMemberId(), "Profile").get(0);
        }
        requestMember.updateProfileImg(profileImg);
        findMember.updateMember(requestMember);

        return memberRepository.save(findMember);
    }

    public Member read(String memberId) {
        return findVerifiedMember(memberId);
    }

    public void delete(String memberId) {
        // 회원이 존재하는지 확인
        Member findMember = findVerifiedMember(memberId);
        // 존재한다면 먼저 해당 회원의 프로필 이미지 삭제
        List<String> fileNameList = imageService.findFilesById("member" ,memberId);
        String profileImg = "";
        if(fileNameList.size() > 0) {
            profileImg = fileNameList.get(0).substring(fileNameList.get(0).lastIndexOf("/") + 1);
        }

        System.out.println("profileImg = " + profileImg);
        imageService.deleteFile(profileImg, "member", memberId);
        // 해당 회원 정보 삭제
        memberRepository.deleteById(memberId);
    }

    public Member findVerifiedMember(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public String emailFind(String phone) {
        Member findMember = memberRepository.findByPhone(phone).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );
        return findMember.getEmail();
    }

    public void passwordChance(String email,String password) {
        Member findMember = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );
        if(!password.equals(findMember.getPassword())) findMember.updatePassword(password);
        else throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_PASSWORD);
    }

    public void emailValidate(String email) {
        boolean isEmail = memberRepository.existsMemberByEmail(email);
        if(!isEmail) throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }

    public void phoneValidate(String phone) {
        boolean isPhone = memberRepository.existsMemberByPhone(phone);
        if(!isPhone) throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }

    public Member findForPhone(String phone) {
        return memberRepository.findByPhone(phone).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );
    }

    public String searchMemberIdByEmail(String email) {
        return memberRepository.findMemberIdByEmail(email);
    }


    public String createMemberId() {

        Member lastMember = memberRepository.findFirstByOrderByMemberIdDesc().orElse(null);
        long identity = lastMember != null ? Long.parseLong(lastMember.getMemberId().substring(1,2)) + 1 : 1L;

        return "M" + identity;
    }
}
