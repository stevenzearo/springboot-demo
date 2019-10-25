package steve.springboot.nosql.elasticsearch.prototype;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steve.springboot.nosql.elasticsearch.domain.Person;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author steve
 */
public class RestHighLevelClientTest {
    private static final Logger logger = LoggerFactory.getLogger(RestHighLevelClientTest.class);
    private static RestHighLevelClient restHighLevelClient = null;

    @BeforeEach
    public void before() {
        restHighLevelClient = new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("localhost", 9200, "http")
            ));
    }

    @Test
    public void create() throws IOException {
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1");
        Person steve = new Person(UUID.randomUUID().toString(), "steve", "123@qq.com", 12);
        indexRequest.source(new Gson().toJson(steve), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        long version = indexResponse.getVersion();
        RestStatus status = indexResponse.status();
        logger.warn("version:" + version + " status:" + status.getStatus());
    }

    @Test
    public void asyncCreate() throws InterruptedException {
        IndexRequest indexRequest2 = new IndexRequest("posts", "doc", "2");
        Person jerry = new Person(UUID.randomUUID().toString(), "jerry", "qwer@gmail.com", 25);
        indexRequest2.source(new Gson().toJson(jerry), XContentType.JSON);
        restHighLevelClient.indexAsync(indexRequest2, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                String id = indexResponse.getId();
                long version = indexResponse.getVersion();
                RestStatus status = indexResponse.status();
                logger.warn("id:" + id + "version:" + version + " status:" + status.getStatus());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(500);
    }

    @Test
    public void get() throws IOException {
        GetRequest getRequest = new GetRequest("posts", "doc", "1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        if (getResponse.isExists()) {
            Map<String, Object> source = getResponse.getSource();
            source.forEach((k, v) -> logger.warn("key:" + k + ", value:" + v));
        }
    }

    @Test
    public void asyncGet() throws InterruptedException {
        GetRequest getRequest = new GetRequest("posts", "doc", "1");
        restHighLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {
                if (documentFields.isExists()) {
                    Map<String, Object> source = documentFields.getSource();
                    source.forEach((k, v) -> logger.warn("key:" + k + ", value:" + v));
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(500);

    }

    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // aggregate
//        TermsAggregationBuilder terms = AggregationBuilders.terms("");
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", "steve");
//        QueryBuilders.matchAllQuery()
//        searchSourceBuilder.aggregation(terms);
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.sort(SortBuilders.fieldSort("age"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        if (searchResponse.status().getStatus() == 200) {
            SearchHits searchHits = searchResponse.getHits();
            searchHits.forEach(searchHit -> {
                searchHit.getSourceAsMap().forEach((k, v) -> logger.warn("key:" + k + ", value:" + v));
            });
        }
    }

    @Test
    public void asyncSearch() throws InterruptedException {
        SearchRequest searchRequest = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        restHighLevelClient.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                if (searchResponse.status().getStatus() == 200) {
                    SearchHits searchHits = searchResponse.getHits();
                    searchHits.forEach(searchHit -> {
                        searchHit.getSourceAsMap().forEach((k, v) -> logger.warn("key:" + k + ", value:" + v));
                    });
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(500);
    }

    @AfterEach
    public void after() throws IOException {
        restHighLevelClient.close();
    }
}
