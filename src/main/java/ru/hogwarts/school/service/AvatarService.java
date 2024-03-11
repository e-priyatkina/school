package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(long id, MultipartFile avatar) throws IOException;

    Avatar findAvatar(long id);

    List<Avatar> findAll(Integer pageNumber, Integer pageSize);
}
