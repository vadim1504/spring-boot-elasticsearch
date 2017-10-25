package by.vadim.spring.elasticsearch.config;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableElasticsearchRepositories(basePackages = "by.vadim.spring.elasticsearch.repository")
public class EsConfig {

    @Bean
    public NodeBuilder nodeBuilder(){
        return new NodeBuilder();
    }

    @Bean(name = "operations")
    public ElasticsearchOperations elasticsearchOperations() throws IOException{
        final Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");
        Settings.Builder elasticsearchSettings =
                Settings.settingsBuilder()
                        .put("http.enabled", "true")
                        .put("index.number_of_shards","1")
                        .put("path.data", tmpDir.toAbsolutePath().toString())
                        .put("path.logs", tmpDir.toAbsolutePath().toString())
                        .put("path.work", tmpDir.toAbsolutePath().toString())
                        .put("path.home", tmpDir);
        return new ElasticsearchTemplate(nodeBuilder()
                .local(true)
                .settings(elasticsearchSettings)
                .node()
                .client()
        );
    }
}
