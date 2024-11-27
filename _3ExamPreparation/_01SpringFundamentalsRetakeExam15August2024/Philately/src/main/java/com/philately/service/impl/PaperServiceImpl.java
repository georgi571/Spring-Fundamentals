package com.philately.service.impl;

import com.google.gson.Gson;
import com.philately.model.dto.imports.PaperSeedDTO;
import com.philately.model.entity.Paper;
import com.philately.model.enums.PaperType;
import com.philately.repository.PaperRepository;
import com.philately.service.PaperService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PaperServiceImpl implements PaperService {
    private final String PATH = "D:\\SoftwareUniversity\\Spring-Fundamentals\\_3ExamPreparation\\_01SpringFundamentalsRetakeExam15August2024\\Philately\\src\\main\\resources\\static\\files\\papers.json";

    private final PaperRepository paperRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public PaperServiceImpl(PaperRepository paperRepository, ModelMapper modelMapper, Gson gson) {
        this.paperRepository = paperRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public String readUserFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH)));
    }

    @Override
    public void seedPapers() throws IOException {
        if (paperRepository.count() == 0) {
            PaperSeedDTO[] paperSeedDTOS = this.gson.fromJson(readUserFile(), PaperSeedDTO[].class);
            for (PaperSeedDTO seedDTO : paperSeedDTOS) {
                Paper paper = this.modelMapper.map(seedDTO, Paper.class);
                PaperType paperType = PaperType.valueOf(seedDTO.getPaperName());
                paper.setPaperName(paperType);
                this.paperRepository.saveAndFlush(paper);
            }
        }
    }

    @Override
    public Paper getPaperByPaperType(PaperType paperType) {
        return this.paperRepository.findByPaperName(paperType);
    }
}
