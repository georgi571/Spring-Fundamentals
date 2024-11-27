package com.philately.service.impl;

import com.google.gson.Gson;
import com.philately.model.dto.AddStampDTO;
import com.philately.model.dto.OfferedStampDTO;
import com.philately.model.dto.imports.StampSeedDTO;
import com.philately.model.entity.Paper;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.model.enums.PaperType;
import com.philately.repository.PaperRepository;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import com.philately.util.CurrentUser;
import com.philately.service.StampService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class StampServiceImpl implements StampService {
    private final String PATH = "D:\\SoftwareUniversity\\Spring-Fundamentals\\_3ExamPreparation\\_01SpringFundamentalsRetakeExam15August2024\\Philately\\src\\main\\resources\\static\\files\\stamps.json";

    private final StampRepository stampRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final PaperRepository paperRepository;
    private final Gson gson;

    public StampServiceImpl(StampRepository stampRepository, ModelMapper modelMapper, CurrentUser currentUser, UserRepository userRepository, PaperRepository paperRepository, Gson gson) {
        this.stampRepository = stampRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.paperRepository = paperRepository;
        this.gson = gson;
    }

    @Override
    public String readStampFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH)));
    }

    @Override
    public void seedStamps() throws IOException {
        if (this.stampRepository.count() == 0) {
            StampSeedDTO[] stampSeedDTOS = this.gson.fromJson(readStampFile(), StampSeedDTO[].class);
            for (StampSeedDTO seedDTO : stampSeedDTOS) {

                Stamp stamp = this.modelMapper.map(seedDTO, Stamp.class);
                stamp.setOwner(this.userRepository.findById(seedDTO.getOwnerId()).orElse(null));
                stamp.setPaper(this.paperRepository.findById(seedDTO.getPaperId()).orElse(null));
                this.stampRepository.saveAndFlush(stamp);
            }
        }
    }

    @Override
    public void createStamp(AddStampDTO addStampDTO) {
        Stamp stamp = this.modelMapper.map(addStampDTO, Stamp.class);
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        PaperType paperType = addStampDTO.getPaper();
        Paper paper = this.paperRepository.findByPaperName(paperType);
        stamp.setOwner(user);
        stamp.setPaper(paper);
        this.stampRepository.saveAndFlush(stamp);
    }

    @Override
    public List<OfferedStampDTO> getAllStampsWhichIsNotFromOwner(CurrentUser currentUser) {
        User user = this.userRepository.findByUsername(currentUser.getUsername()).get();
        List<Stamp> allByOwnerIsNot = this.stampRepository.findAllByOwnerIsNot(user);
        List<OfferedStampDTO> offeredStampDTOS = new ArrayList<>();
        allByOwnerIsNot.forEach(stamp -> {
            if (stamp.getBuyer() == null) {
                OfferedStampDTO offeredStampDTO = this.modelMapper.map(stamp, OfferedStampDTO.class);
                offeredStampDTOS.add(offeredStampDTO);
            }
        });
        return offeredStampDTOS;
    }

    @Override
    public Stamp getStampById(Long id) {
        return this.stampRepository.findById(id).get();
    }
}
