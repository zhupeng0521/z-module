package com.module.zhupeng.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * ResponseBuilder
 * <p>date : 2019-11-23</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class ResponseBuilder {

    private volatile Map<String, Object> body;

    public ResponseBuilder setBody(Map<String, Object> body) {
        this.body = body;
        return this;
    }

    public Map<String, Object> getBody() {
        if (null == body) {
            body = new HashMap<>(8);
        }
        return body;
    }


    public static ResponseBuilder getEntity() {
        return new ResponseBuilder();
    }

    /**
     * 500 内部服务错误
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> internalServerError() {
        return status(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }


    /**
     * 422 UNPROCESSABLE ENTITY
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> unprocessableEntity() {
        return status(HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
    }

    /**
     * 422 UNPROCESSABLE ENTITY
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> unprocessableEntity(final T body) {
        return status(HttpStatus.UNPROCESSABLE_ENTITY, body);
    }

    /**
     * 500 内部服务错误
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> internalServerError(final T body) {
        return status(HttpStatus.INTERNAL_SERVER_ERROR, body);
    }


    /**
     * 404 Not Found 未找到
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> notFound() {
        return status(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    /**
     * 404 Not Found 未找到
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> notFound(final T body) {
        return status(HttpStatus.NOT_FOUND, body);
    }


    /**
     * 403 禁止访问
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> forbidden() {
        return status(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
    }

    /**
     * 403 禁止访问
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> forbidden(final T body) {
        return status(HttpStatus.FORBIDDEN, body);
    }

    /**
     * 401 未授权
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> unauthorized() {
        return status(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    /**
     * 401 未授权
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> unauthorized(final T body) {
        return status(HttpStatus.UNAUTHORIZED, body);
    }


    /**
     * 400 错误的请求
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> badRequest() {
        return status(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    /**
     * 400 错误的请求
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> badRequest(final T body) {
        return status(HttpStatus.BAD_REQUEST, body);
    }

    /**
     * 203 NO CONTENT
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> noContent() {
        return status(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.getReasonPhrase());
    }

    /**
     * 203 NO CONTENT
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> noContent(final T body) {
        return status(HttpStatus.NO_CONTENT, body);
    }


    /**
     * 202 Accepted
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> accepted() {
        return status(HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.getReasonPhrase());
    }

    /**
     * 202 Accepted
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> accepted(final T body) {
        return status(HttpStatus.ACCEPTED, body);
    }

    /**
     * 201 created
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> created() {
        return status(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase());
    }

    /**
     * 201 created
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> created(final T body) {
        return status(HttpStatus.CREATED, body);
    }

    /**
     * 200 OK
     *
     * @return ResponseEntity
     */
    public static ResponseEntity<String> ok() {
        return status(HttpStatus.OK, HttpStatus.OK.getReasonPhrase());
    }

    /**
     * 200 OK
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity<T> ok(final T body) {
        return status(HttpStatus.OK, body);
    }


    /**
     * 200 OK rest Success
     *
     * @return ResponseEntity
     */
    public static ResponseEntity success() {
        return ok(RBuilder.success());
    }

    /**
     * 200 OK rest Success
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity success(final T body) {
        return status(HttpStatus.OK, RBuilder.success(body));
    }

    /**
     * 200 OK rest Failed
     *
     * @return ResponseEntity
     */
    public static ResponseEntity failed() {
        return ok(RBuilder.failed());
    }

    /**
     * 200 OK rest Failed
     *
     * @param body body数据
     * @param <T>  body类型
     * @return ResponseEntity
     */
    public static <T> ResponseEntity failed(final T body) {
        return status(HttpStatus.OK, RBuilder.failed().setData(body));
    }

    /**
     * 200 OK rest
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<R<T>> rest(final R<T> body) {
        return status(HttpStatus.OK, body);
    }


    public static boolean isOk(ResponseEntity responseEntity) {
        if (Objects.isNull(responseEntity) || Objects.isNull(responseEntity.getStatusCode())) {
            return false;
        }
        return HttpStatus.OK.equals(responseEntity.getStatusCode());
    }

    public static boolean isNotOk(ResponseEntity responseEntity) {
        return !isOk(responseEntity);
    }


    public ResponseEntity<Map<String, Object>> buildNotFound() {
        return ResponseBuilder.notFound(this.body);
    }

    public ResponseEntity<Map<String, Object>> buildAccepted() {
        return ResponseBuilder.accepted(this.body);
    }

    public ResponseEntity<Map<String, Object>> buildNoContent() {
        return ResponseBuilder.noContent(this.body);
    }

    public ResponseEntity<Map<String, Object>> buildUnprocessableEntity() {
        return ResponseBuilder.unprocessableEntity(this.body);
    }

    public ResponseEntity<Map<String, Object>> buildBadRequest() {
        return ResponseBuilder.badRequest(this.body);
    }

    public ResponseEntity<Map<String, Object>> buildInternalServerError() {
        return ResponseBuilder.internalServerError(this.body);
    }

    public ResponseEntity<Map<String, Object>> buildByStatus(final HttpStatus status) {
        return ResponseBuilder.status(status, this.body);
    }

    public ResponseEntity<Map<String, Object>> buildByStatus(final int status) {
        return ResponseBuilder.status(status, this.body);
    }

    public ResponseEntity<Map<String, Object>> buildOk() {
        return ResponseBuilder.ok(this.body);
    }

    public ResponseBuilder add(final String key, final Object value) {
        if (null == this.body) {
            this.body = new HashMap<>(8);
        }
        this.body.put(key, value);
        return this;
    }

    private static ResponseEntity.BodyBuilder buildStatus(int status) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(status);

        // builder.contentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        builder.contentType(MediaType.APPLICATION_JSON);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAcceptCharset(Arrays.asList(StandardCharsets.UTF_8));
//        builder.headers(httpHeaders);
        return builder;
    }

    public static <T> ResponseEntity<T> status(final int status) {
        return buildStatus(status).build();
    }

    public static <T> ResponseEntity<T> status(final int status, final T body) {
        return buildStatus(status).body(body);
    }

    public static <T> ResponseEntity<T> status(final HttpStatus status) {
        return status(status.value());
    }

    public static <T> ResponseEntity<T> status(final HttpStatus status, final T body) {
        return status(status.value(), body);
    }

}
