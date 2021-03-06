package libai.wxapp.clear.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//配置mybatis mapper的扫描路径
@MapperScan("libai.wxapp.clear.dao")
public class DataSourceConf {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private  String jdbcPassword;
    @Bean(name="dataSource")
    public ComboPooledDataSource createDb() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        //关闭连接后不自动提交
        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }
}
