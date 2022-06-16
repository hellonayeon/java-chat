package dto.request;

import dto.type.DtoType;

public class ProgramExitRequest extends DTO {

    String userId;

    public ProgramExitRequest(String userId) {
        super(DtoType.PROGRAM_EXIT);

        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() + userId;
    }
}
