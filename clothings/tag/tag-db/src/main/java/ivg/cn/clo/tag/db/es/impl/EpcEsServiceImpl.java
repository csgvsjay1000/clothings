package ivg.cn.clo.tag.db.es.impl;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import ivg.cn.clo.tag.db.es.EpcEsService;
import ivg.cn.clo.tag.model.entify.BTag;

@Service
public class EpcEsServiceImpl implements EpcEsService{

	@Autowired
	private TransportClient transportClient;
	
	@Override
	public void put(List<BTag> tags) {
		
		BulkRequestBuilder builder = transportClient.prepareBulk();
		for (BTag bTag : tags) {
			builder.add(transportClient.prepareIndex(EPC_INDEX, EPC_INDEX_TYPE, String.valueOf(bTag.getFid()))
					.setSource(JSON.toJSONString(bTag), XContentType.JSON));
		}
		BulkResponse response = builder.execute().actionGet();
		System.out.println(response);
	}

}
