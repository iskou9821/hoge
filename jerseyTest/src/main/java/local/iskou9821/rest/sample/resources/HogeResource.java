package local.iskou9821.rest.sample.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import local.iskou9821.rest.sample.models.Hoge;
import local.iskou9821.rest.sample.models.Hoges;

@Path("/hoges")
public class HogeResource {
	@Context
	public UriInfo uri;
	
	@Context
	public Request req;
	
	/*
	 * 非常にダメな実装ですけど、まあサンプルなので。。。。
	 */
	private static Map<Long, Hoge> HOGE_MAP;
	private static long NEXT_ID=100;
	static {
		HOGE_MAP = new HashMap<Long, Hoge>();
		HOGE_MAP.put(1L, new Hoge(1L, "hoge", "hogehoge1", 1));
		HOGE_MAP.put(2L, new Hoge(2L, "hoge-hoge", "hogehoge2", 2));
		HOGE_MAP.put(3L, new Hoge(3L, "hoge-hoge-hoge", "hogehoge3", 3));
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Hoges[] findAllHoges( //@QueryParam → リクエストパラメータ
		@QueryParam("cond1")String cond1, @QueryParam("cond2")String cond2, @QueryParam("cond3")String cond3) {
		
		System.out.println("GET:" + uri.getRequestUri());
		System.out.println("PARAMS:=" + cond1 + " / " + cond2 + " / " + cond3);
		
		//クラスが違うのはJSONのルート要素を複数形に変える必要がるため、
		List<Hoges> tmp = new ArrayList<>();
		for (Hoge hoge : HOGE_MAP.values()) {
			tmp.add(new Hoges(hoge));
		}
		
		return tmp.toArray(new Hoges[0]);
	}
	
	@Path("{id}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Hoge[] findHoge(@PathParam("id")Long id) { //websocketパッケージにもPathParamというアノテーションがあるので注意！！！
		System.out.println(uri.getRequestUri());
		
		Hoge hoge = HOGE_MAP.get(id);
		
		//検索結果が1件だけの場合でもember-dataが要求するJSONの仕様上、配列にする必要がある。
		return new Hoge[] { hoge };
	}
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public Hoge[] createHoge(Hoge[] hoge) {
		System.out.println(uri.getRequestUri());
		System.out.println("ADDED!!");
		
		//配列になるのはember-dataの仕様。要素数は必ず一つだけのはず。
		Hoge newHoge = hoge[0];
		System.out.println(newHoge.getMsg1() + " / " + newHoge.getMsg2() + " / " + newHoge.getNum1());
		newHoge.setId(NEXT_ID++);
		HOGE_MAP.put(newHoge.getId(), newHoge);
		
		return new Hoge[] { newHoge };
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public Hoge[] updateHoge(@PathParam("id")Long id, Hoge[] hoge) {
		System.out.println(uri.getRequestUri());
		System.out.println("Updated!!:" + id);
		
		Hoge upd = hoge[0];
		upd.setId(id);
		HOGE_MAP.put(id, upd);
		return new Hoge[] {
				upd
			};
	}
	
	@DELETE
	@Path("{id}")
	public void deleteHoge(@PathParam("id")Long id) {
		System.out.println(uri.getRequestUri());
		HOGE_MAP.remove(id);
	}
}
