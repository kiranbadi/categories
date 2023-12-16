package dropwizard.client;

import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;



@Data
public class Footer {

    private Integer id;
    private String footer_links;
    private String footer_link_text;

    private String footer_link_id;

    private String footer_links_ref;

    private String footer_link_purpose;

    private String footer_link_location;

    private String footer_link_status;

    @ColumnName("created_at")
    private String created_at;

    @ColumnName("updated_at")
    private String updated_at;

    @ColumnName("created_by")
    private String created_by;

    @ColumnName("updated_by")
    private String updated_by;

}
