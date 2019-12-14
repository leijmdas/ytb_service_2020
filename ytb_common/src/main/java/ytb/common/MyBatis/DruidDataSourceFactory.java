package ytb.common.MyBatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 自定义类，用于将druid集成到配置文件中
 * @author Administrator
 *
 */
public final class DruidDataSourceFactory implements DataSourceFactory
{
    private Properties props;

    @Override
    public void setProperties(Properties props)
    {
        this.props = props;
    }

    @Override
    public DataSource getDataSource()
    {
        DruidDataSource dds = new DruidDataSource();
        dds.setDefaultAutoCommit(false);

        dds.setInitialSize(0);
        dds.setMaxActive(20);
        dds.setMinIdle(5);
        dds.setValidationQuery("select 1");
        dds.setTestWhileIdle(true);

        dds.setDriverClassName(this.props.getProperty("driver"));
        dds.setUrl(this.props.getProperty("url"));
        dds.setUsername(this.props.getProperty("username"));
        dds.setPassword(this.props.getProperty("password"));

        // 其他配置可以根据MyBatis主配置文件进行配置
        try
        {
            dds.init();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dds;
    }
}
