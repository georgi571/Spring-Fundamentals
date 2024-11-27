package com.philately.service;

import com.philately.model.dto.AddStampDTO;
import com.philately.model.dto.OfferedStampDTO;
import com.philately.model.entity.Stamp;
import com.philately.util.CurrentUser;

import java.io.IOException;
import java.util.List;

public interface StampService {
    String readStampFile() throws IOException;

    void seedStamps() throws IOException;

    void createStamp(AddStampDTO addStampDTO);

    List<OfferedStampDTO> getAllStampsWhichIsNotFromOwner(CurrentUser currentUser);

    Stamp getStampById(Long id);
}
