package mk.ukim.finki.emt.labs.demo.Model.DTO;

public class CategoryCountDTO {

    private String category;
    private Long count;

    public CategoryCountDTO(String category, Long count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public Long getCount() {
        return count;
    }
}
