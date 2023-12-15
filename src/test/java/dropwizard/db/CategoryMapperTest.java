package dropwizard.db;

import dropwizard.client.Category;
import org.jdbi.v3.core.statement.StatementContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class CategoryMapperTest {

    @Mock
    private ResultSet resultSet;

    @Mock
    private StatementContext statementContext;

    @Test
    public void testMap() throws SQLException {
        assertNotNull(resultSet, "ResultSet cannot be null");
        assertNotNull(statementContext, "StatementContext cannot be null");
        CategoryMapper categoryMapper = new CategoryMapper();
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getLong("category_id")).thenReturn(123L);
        when(resultSet.getString("category_name")).thenReturn("Example Category");
        when(resultSet.getString("category_description")).thenReturn("Description");
        when(resultSet.getString("category_status")).thenReturn("Active");
        when(resultSet.getString("created_at")).thenReturn("2023-01-01");
        when(resultSet.getString("updated_at")).thenReturn("2023-01-02");
        when(resultSet.getString("created_by")).thenReturn("John");
        when(resultSet.getString("updated_by")).thenReturn("Alice");

        // Act
        Category category = categoryMapper.map(resultSet, statementContext);

        // Assert
        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals(123L, category.getCategory_id());
        assertEquals("Example Category", category.getCategory_name());
        assertEquals("Description", category.getCategory_description());
        assertEquals("Active", category.getCategory_status());
        assertEquals("2023-01-01", category.getCreated_at());
        assertEquals("2023-01-02", category.getUpdated_at());
        assertEquals("John", category.getCreated_by());
        assertEquals("Alice", category.getUpdated_by());

        // Verify that ResultSet methods were called
        verify(resultSet).getLong("id");
        verify(resultSet).getLong("category_id");
        verify(resultSet).getString("category_name");
        verify(resultSet).getString("category_description");
        verify(resultSet).getString("category_status");
        verify(resultSet).getString("created_at");
        verify(resultSet).getString("updated_at");
        verify(resultSet).getString("created_by");
        verify(resultSet).getString("updated_by");

        // Verify that other ResultSet methods were not called
        verifyNoMoreInteractions(resultSet);
    }
}
