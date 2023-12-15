package dropwizard.client;

import lombok.*;
import org.jdbi.v3.core.mapper.reflect.ColumnName;




@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @ColumnName("id")
    private Long id;

    @ColumnName("category_id")
    private Long category_id;

    @ColumnName("category_name")
    private String category_name;
    @ColumnName("category_description")
    private String category_description;
    @ColumnName("category_status")
    private String category_status;

    @ColumnName("created_at")
    private String created_at;

    @ColumnName("updated_at")
    private String updated_at;

    @ColumnName("created_by")
    private String created_by;

    @ColumnName("updated_by")
    private String updated_by;
}
