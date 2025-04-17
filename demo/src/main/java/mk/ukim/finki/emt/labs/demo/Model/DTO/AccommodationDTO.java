package mk.ukim.finki.emt.labs.demo.Model.DTO;

import mk.ukim.finki.emt.labs.demo.Model.enums.Category;

public class AccommodationDTO {

    private String name;

    private Category category;

    private Long hostId;

    private int numRooms;

    public AccommodationDTO(String name, Category category, Long hostId, int numRooms) {
        this.name = name;
        this.category = category;
        this.hostId = hostId;
        this.numRooms = numRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }
}
