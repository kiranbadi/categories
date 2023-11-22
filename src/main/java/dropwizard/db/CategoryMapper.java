package dropwizard.db;

import dropwizard.client.Category;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    public static final CategoryMapper factory = new CategoryMapper();

    @Override
    public Category map(ResultSet rs, StatementContext ctx) throws SQLException {
        return  Category.builder()
                        .id(rs.getLong("id"))
                        .category_id(rs.getLong("category_id"))
                        .category_name(rs.getString("category_name"))
                        .category_description(rs.getString("category_description"))
                        .category_status(rs.getString("category_status"))
                        .created_at(rs.getString("created_at"))
                        .updated_at(rs.getString("updated_at"))
                        .created_by(rs.getString("created_by"))
                        .updated_by(rs.getString("updated_by"))
                        .build();

    }
}
