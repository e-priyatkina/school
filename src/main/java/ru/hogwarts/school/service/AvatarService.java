package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(long id, MultipartFile avatar) throws IOException;

    Avatar findAvatar(long id);
}
