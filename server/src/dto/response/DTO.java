package dto.response;

import dto.type.DtoType;

public abstract class DTO {
    DtoType type;

    public DTO(DtoType type) {
        this.type = type;
    }

    public DtoType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + ":";
    }
}
