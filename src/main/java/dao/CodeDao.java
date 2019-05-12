package dao;

import model.Code;

import java.util.ArrayList;
import java.util.List;

public class CodeDao {

    private static final List<Code> codes = new ArrayList<>();

    public void addCode(Code code) {
        codes.add(code);
    }

    public boolean checkCode(Code code) {
        return codes.contains(code);
    }
}
