package com.energizeglobal.itpm.service.impl;

import com.energizeglobal.itpm.model.FileEntity;
import com.energizeglobal.itpm.model.TaskEntity;
import com.energizeglobal.itpm.model.dto.FileInfoDto;
import com.energizeglobal.itpm.repository.FileInfoRepository;
import com.energizeglobal.itpm.service.FileService;
import com.energizeglobal.itpm.service.Mapper;
import com.energizeglobal.itpm.service.TaskService;
import com.energizeglobal.itpm.util.exceptions.FileSystemException;
import com.energizeglobal.itpm.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final TaskService taskService;
    private final FileInfoRepository fileInfoRepository;
    private final Mapper mapper;


    @Override
    @Transactional
    public void saveFile(MultipartFile file, Long taskId) {

        final FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());

        final TaskEntity taskEntity = taskService.findEntityById(taskId);
        fileEntity.setOwnerTaskEntity(taskEntity);

        try {
            fileEntity.setFile(file.getBytes());
        } catch (IOException exception) {
            throw new FileSystemException(exception.getMessage());
        }

        fileInfoRepository.save(fileEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public byte[] getFile(Long fileInfoId) {
        final FileEntity fileEntity = findEntityById(fileInfoId);

        return fileEntity.getFile();
    }

    @Override
    public List<FileInfoDto> getAllByTaskId(Long taskId) {
        final TaskEntity taskEntity = taskService.findEntityById(taskId);

        return fileInfoRepository.findAllByOwnerTaskEntityAndIsDeletedFalse(taskEntity)
                .stream()
                .map(fileEntity -> mapper.map(fileEntity, new FileInfoDto()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FileEntity findEntityById(Long fileInfoId) {
        return fileInfoRepository.findById(fileInfoId)
                .orElseThrow(() -> new NotFoundException("File with id: " + fileInfoId + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public FileInfoDto findById(Long fileId) {
        final FileEntity infoEntity = findEntityById(fileId);
        return mapper.map(infoEntity, new FileInfoDto());
    }

    @Override
    @Transactional
    public void deleteFile(Long fileId) {
        fileInfoRepository.deleteById(fileId);
    }
}
