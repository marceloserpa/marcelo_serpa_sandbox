package com.marceloserpa.armeniapoc;

import com.linecorp.armeria.common.AggregatedHttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.annotation.Post;

public class BookApi {

	@Post("/books")
	public HttpResponse books(AggregatedHttpRequest aggregatedRequest) {
		String contentUtf8 = aggregatedRequest.contentUtf8();
		System.out.println("-------");
		System.out.println(contentUtf8);
		System.out.println("-------");
		return HttpResponse.of(HttpStatus.OK, MediaType.PLAIN_TEXT_UTF_8, "POST top, %s!");
	}

}