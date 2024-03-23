package com.energizeglobal.itpm.service;

import com.energizeglobal.itpm.model.CustomOAuth2User;
import com.energizeglobal.itpm.model.CustomOidcUser;
import com.energizeglobal.itpm.model.UserEntity;
import com.energizeglobal.itpm.model.dto.UserDto;
import com.energizeglobal.itpm.model.dto.UserProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUser(UserDto userDto);

    void updateUser(UserDto userDto);

    UserDto findById(String userId);

    UserEntity findEntityById(String userId);

    void changeActivationStatus(String userId, Boolean status);

    Page<UserProjectDto> findAllUsersByProject(String projectId, Pageable pageable);

    List<UserProjectDto> findAllProjectsOfUser(String userId);


    void sendMailNotificationOfComment(String userId, Long taskId);

    UserEntity findByEmail(String email);

    List<UserDto> search(String searchString);

    List<UserDto> searchInProject(String searchString, String projectId);

    CustomOAuth2User processOAuth2User(OAuth2User user);

    CustomOidcUser processOidcUser(OidcUser user);
}
