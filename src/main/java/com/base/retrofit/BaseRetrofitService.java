package com.base.retrofit;

import com.base.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
@Slf4j
public class BaseRetrofitService {
   public <T extends RetrofitBaseResponse> RetrofitBaseResponse getResponseOrLogWarning(Call<RetrofitBaseResponse> call, String failMessage) throws RetrofitException {
        try {
            return getResponse(call);
        } catch (Exception e) {
            log.error("PaasRetrofitService getResponseOrLogWarning - ", e);
            throw new RetrofitException(1, failMessage);
        }
    }

    private <T extends RetrofitBaseResponse> T getResponse(Call<T> call) throws RetrofitException {
        try {
            Response<T> execute = call.execute();
            if (execute.isSuccessful()) {

                return execute.body();
            }
            log.error("PaasRetrofitService getResponse - STATUS CODE:",execute.code()+"/n"+ Utility.jsonEncode(execute.errorBody()));
            throw new RetrofitException(1, "PAAS apis replied failure - " + Utility.jsonEncode(execute.errorBody()));
        } catch (IOException e) {
            log.error("Exception getResponse in calling PAAS - ", e);
            throw new RetrofitException(1, e.getMessage());
        }
    }

}
