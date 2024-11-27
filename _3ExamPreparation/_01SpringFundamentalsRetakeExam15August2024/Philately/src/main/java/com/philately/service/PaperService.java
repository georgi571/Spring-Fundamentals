package com.philately.service;

import com.philately.model.entity.Paper;
import com.philately.model.enums.PaperType;

import java.io.IOException;

public interface PaperService {

    String readUserFile() throws IOException;

    void seedPapers() throws IOException;

    Paper getPaperByPaperType(PaperType paperType);
}
