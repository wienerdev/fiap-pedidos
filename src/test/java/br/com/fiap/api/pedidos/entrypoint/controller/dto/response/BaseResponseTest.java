package br.com.fiap.api.pedidos.entrypoint.controller.dto.response;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BaseResponseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BaseResponse#BaseResponse()}
     *   <li>{@link BaseResponse#setResponse(Object)}
     *   <li>{@link BaseResponse#setSuccess(boolean)}
     *   <li>{@link BaseResponse#getResponse()}
     *   <li>{@link BaseResponse#isSuccess()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        BaseResponse<Object> actualBaseResponse = new BaseResponse<>();
        actualBaseResponse.setResponse("Response");
        actualBaseResponse.setSuccess(true);
        actualBaseResponse.getResponse();
        assertTrue(actualBaseResponse.isSuccess());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BaseResponse#BaseResponse(boolean, Object)}
     *   <li>{@link BaseResponse#setResponse(Object)}
     *   <li>{@link BaseResponse#setSuccess(boolean)}
     *   <li>{@link BaseResponse#getResponse()}
     *   <li>{@link BaseResponse#isSuccess()}
     * </ul>
     */
    @Test
    public void testConstructor2() {
        BaseResponse<Object> actualBaseResponse = new BaseResponse<>(true, "Response");
        actualBaseResponse.setResponse("Response");
        actualBaseResponse.setSuccess(true);
        actualBaseResponse.getResponse();
        assertTrue(actualBaseResponse.isSuccess());
    }
}

