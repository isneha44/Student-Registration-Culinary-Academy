package com.culinarry.registration.service;

import com.culinarry.registration.dao.ProgramDAO;
import com.culinarry.registration.entity.Program;

import java.util.List;

public class ProgramService {

    private ProgramDAO programDAO;

    public ProgramService() {
        this.programDAO = new ProgramDAO();
    }

    public List<Program> getAllPrograms() {
        return programDAO.getAllPrograms();
    }

    public Program getProgramById(String programId) {
        List<Program> programs = getAllPrograms();
        return programs.stream()
                .filter(p -> p.getProgramId().equals(programId))
                .findFirst()
                .orElse(null);
    }
}
