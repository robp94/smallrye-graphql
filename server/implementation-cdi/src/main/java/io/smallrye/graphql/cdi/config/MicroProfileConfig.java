package io.smallrye.graphql.cdi.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.microprofile.config.ConfigProvider;

import io.smallrye.graphql.spi.config.Config;
import io.smallrye.graphql.spi.config.LogPayloadOption;

/**
 * Configuration for GraphQL
 *
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
public class MicroProfileConfig implements Config {

    private Optional<List<String>> hideList;
    private Optional<List<String>> showList;
    private String defaultErrorMessage;
    private Boolean printDataFetcherException;
    private Boolean allowGet;
    private Boolean allowPostWithQueryParameters;
    private Boolean metricsEnabled;
    private Boolean tracingEnabled;
    private Boolean validationEnabled;
    private Boolean eventsEnabled;
    private Boolean federationEnabled;
    private Boolean includeScalarsInSchema;
    private Boolean includeDirectivesInSchema;
    private Boolean includeSchemaDefinitionInSchema;
    private Boolean includeIntrospectionTypesInSchema;
    private LogPayloadOption logPayload;
    private String fieldVisibility;
    private Optional<List<String>> unwrapExceptions;
    private Optional<List<String>> errorExtensionFields;
    private Optional<Integer> parserMaxTokens;
    private Optional<Integer> parserMaxWhitespaceTokens;
    private Optional<Boolean> parserCaptureSourceLocation;
    private Optional<Boolean> parserCaptureLineComments;
    private Optional<Boolean> parserCaptureIgnoredChars;
    private Optional<Integer> queryComplexityInstrumentation;
    private Optional<Integer> queryDepthInstrumentation;

    @Override
    public String getName() {
        return "MicroProfile Config Service";
    }

    @Override
    public String getDefaultErrorMessage() {
        if (defaultErrorMessage == null) {
            defaultErrorMessage = getStringConfigValue(ConfigKey.DEFAULT_ERROR_MESSAGE, Config.SERVER_ERROR_DEFAULT_MESSAGE);
        }
        return defaultErrorMessage;
    }

    @Override
    public boolean isPrintDataFetcherException() {
        if (printDataFetcherException == null) {
            printDataFetcherException = getBooleanConfigValue(ConfigKey.PRINT_DATAFETCHER_EXCEPTION);
        }
        return printDataFetcherException;
    }

    @Override
    public Optional<List<String>> getHideErrorMessageList() {
        if (hideList == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            Optional<List<String>> blackList = microProfileConfig.getOptionalValues(ConfigKey.EXCEPTION_BLACK_LIST,
                    String.class);
            hideList = mergeList(microProfileConfig.getOptionalValues("mp.graphql.hideErrorMessage", String.class), blackList);
        }
        return hideList;
    }

    @Override
    public Optional<List<String>> getShowErrorMessageList() {
        if (showList == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            Optional<List<String>> whiteList = microProfileConfig.getOptionalValues(ConfigKey.EXCEPTION_WHITE_LIST,
                    String.class);
            showList = mergeList(microProfileConfig.getOptionalValues("mp.graphql.showErrorMessage", String.class), whiteList);
        }
        return showList;
    }

    @Override
    public boolean isAllowGet() {
        if (allowGet == null) {
            allowGet = getBooleanConfigValue(ConfigKey.ALLOW_GET);
        }
        return allowGet;
    }

    @Override
    public boolean isAllowPostWithQueryParameters() {
        if (allowPostWithQueryParameters == null) {
            allowPostWithQueryParameters = getBooleanConfigValue(ConfigKey.ALLOW_POST_WITH_QUERY_PARAMETERS);
        }
        return allowPostWithQueryParameters;
    }

    @Override
    public boolean isMetricsEnabled() {
        if (metricsEnabled == null) {
            metricsEnabled = getBooleanConfigValue(ConfigKey.ENABLE_METRICS);
        }
        return metricsEnabled;
    }

    @Override
    public boolean isTracingEnabled() {
        if (tracingEnabled == null) {
            tracingEnabled = getBooleanConfigValue(ConfigKey.ENABLE_TRACING);
        }
        return tracingEnabled;
    }

    @Override
    public boolean isValidationEnabled() {
        if (validationEnabled == null) {
            validationEnabled = getBooleanConfigValue(ConfigKey.ENABLE_VALIDATION);
        }
        return validationEnabled;
    }

    @Override
    public boolean isEventsEnabled() {
        if (eventsEnabled == null) {
            eventsEnabled = getBooleanConfigValue(ConfigKey.ENABLE_EVENTS);
        }
        return eventsEnabled;
    }

    @Override
    public boolean isFederationEnabled() {
        if (federationEnabled == null) {
            federationEnabled = getBooleanConfigValue(ConfigKey.ENABLE_FEDERATION, true);
        }
        return federationEnabled;
    }

    @Override
    public boolean isIncludeScalarsInSchema() {
        if (includeScalarsInSchema == null) {
            includeScalarsInSchema = getBooleanConfigValue(ConfigKey.SCHEMA_INCLUDE_SCALARS, true);
        }
        return includeScalarsInSchema;
    }

    @Override
    public boolean isIncludeDirectivesInSchema() {
        if (includeDirectivesInSchema == null) {
            includeDirectivesInSchema = getBooleanConfigValue(ConfigKey.SCHEMA_INCLUDE_DIRECTIVES);
        }
        return includeDirectivesInSchema;
    }

    @Override
    public boolean isIncludeSchemaDefinitionInSchema() {
        if (includeSchemaDefinitionInSchema == null) {
            includeSchemaDefinitionInSchema = getBooleanConfigValue(ConfigKey.SCHEMA_INCLUDE_DEFINITION);
        }
        return includeSchemaDefinitionInSchema;
    }

    @Override
    public boolean isIncludeIntrospectionTypesInSchema() {
        if (includeIntrospectionTypesInSchema == null) {
            includeIntrospectionTypesInSchema = getBooleanConfigValue(ConfigKey.SCHEMA_INCLUDE_INTROSPECTION_TYPES);
        }
        return includeIntrospectionTypesInSchema;
    }

    @Override
    public LogPayloadOption logPayload() {
        if (logPayload == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            logPayload = microProfileConfig.getOptionalValue(ConfigKey.LOG_PAYLOAD, LogPayloadOption.class)
                    .orElse(LogPayloadOption.off);
        }
        return logPayload;
    }

    @Override
    public Optional<Boolean> isParserCaptureIgnoredChars() {
        if (parserCaptureIgnoredChars == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            parserCaptureIgnoredChars = microProfileConfig.getOptionalValue(ConfigKey.PARSER_CAPTURE_IGNORED_CHARS,
                    Boolean.class);
        }
        return parserCaptureIgnoredChars;
    }

    @Override
    public Optional<Boolean> isParserCaptureLineComments() {
        if (parserCaptureLineComments == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            parserCaptureLineComments = microProfileConfig.getOptionalValue(ConfigKey.PARSER_CAPTURE_LINE_COMMENTS,
                    Boolean.class);
        }
        return parserCaptureLineComments;
    }

    @Override
    public Optional<Boolean> isParserCaptureSourceLocation() {
        if (parserCaptureSourceLocation == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            parserCaptureSourceLocation = microProfileConfig.getOptionalValue(ConfigKey.PARSER_CAPTURE_SOURCE_LOCATION,
                    Boolean.class);
        }
        return parserCaptureSourceLocation;
    }

    @Override
    public Optional<Integer> getParserMaxTokens() {
        if (parserMaxTokens == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            parserMaxTokens = microProfileConfig.getOptionalValue(ConfigKey.PARSER_MAX_TOKENS, Integer.class);
        }
        return parserMaxTokens;
    }

    @Override
    public Optional<Integer> getParserMaxWhitespaceTokens() {
        if (parserMaxWhitespaceTokens == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            parserMaxWhitespaceTokens = microProfileConfig.getOptionalValue(ConfigKey.PARSER_MAX_WHITESPACE_TOKENS,
                    Integer.class);
        }
        return parserMaxWhitespaceTokens;
    }

    @Override
    public String getFieldVisibility() {
        if (fieldVisibility == null) {
            fieldVisibility = getStringConfigValue(ConfigKey.FIELD_VISIBILITY, Config.FIELD_VISIBILITY_DEFAULT);
        }
        return fieldVisibility;
    }

    @Override
    public Optional<List<String>> getUnwrapExceptions() {
        if (unwrapExceptions == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            unwrapExceptions = microProfileConfig.getOptionalValues(ConfigKey.UNWRAP_EXCEPTIONS, String.class);
        }
        return unwrapExceptions;
    }

    @Override
    public Optional<List<String>> getErrorExtensionFields() {
        if (errorExtensionFields == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            errorExtensionFields = microProfileConfig.getOptionalValues(ConfigKey.ERROR_EXTENSION_FIELDS, String.class);
        }
        return errorExtensionFields;
    }

    @Override
    public Optional<Integer> getQueryComplexityInstrumentation() {
        if (queryComplexityInstrumentation == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            queryComplexityInstrumentation = microProfileConfig.getOptionalValue(ConfigKey.INSTRUMENTATION_QUERY_COMPLEXITY,
                    Integer.class);
        }
        return queryComplexityInstrumentation;
    }

    @Override
    public Optional<Integer> getQueryDepthInstrumentation() {
        if (queryDepthInstrumentation == null) {
            org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
            queryDepthInstrumentation = microProfileConfig.getOptionalValue(ConfigKey.INSTRUMENTATION_QUERY_DEPTH,
                    Integer.class);
        }
        return queryDepthInstrumentation;
    }

    @Override
    public <T> T getConfigValue(String key, Class<T> type, T defaultValue) {
        org.eclipse.microprofile.config.Config microProfileConfig = ConfigProvider.getConfig();
        return microProfileConfig.getOptionalValue(key, type).orElse(defaultValue);
    }

    public void setHideErrorMessageList(Optional<List<String>> hideList) {
        this.hideList = hideList;
    }

    public void setShowErrorMessageList(Optional<List<String>> showList) {
        this.showList = showList;
    }

    public void setDefaultErrorMessage(String defaultErrorMessage) {
        this.defaultErrorMessage = defaultErrorMessage;
    }

    public void setPrintDataFetcherException(boolean printDataFetcherException) {
        this.printDataFetcherException = printDataFetcherException;
    }

    public void setAllowGet(boolean allowGet) {
        this.allowGet = allowGet;
    }

    public void setAllowPostWithQueryParameters(boolean allowPostWithQueryParameters) {
        this.allowPostWithQueryParameters = allowPostWithQueryParameters;
    }

    public void setMetricsEnabled(boolean metricsEnabled) {
        this.metricsEnabled = metricsEnabled;
    }

    public void setTracingEnabled(final boolean tracingEnabled) {
        this.tracingEnabled = tracingEnabled;
    }

    public void setIncludeScalarsInSchema(boolean includeScalarsInSchema) {
        this.includeScalarsInSchema = includeScalarsInSchema;
    }

    public void setIncludeDirectivesInSchema(boolean includeDirectivesInSchema) {
        this.includeDirectivesInSchema = includeDirectivesInSchema;
    }

    public void setIncludeSchemaDefinitionInSchema(boolean includeSchemaDefinitionInSchema) {
        this.includeSchemaDefinitionInSchema = includeSchemaDefinitionInSchema;
    }

    public void setIncludeIntrospectionTypesInSchema(boolean includeIntrospectionTypesInSchema) {
        this.includeIntrospectionTypesInSchema = includeIntrospectionTypesInSchema;
    }

    public void setLogPayload(LogPayloadOption logPayload) {
        this.logPayload = logPayload;
    }

    public void setParserCaptureIgnoredChars(Optional<Boolean> parserCaptureIgnoredChars) {
        this.parserCaptureIgnoredChars = parserCaptureIgnoredChars;
    }

    public void setParserCaptureLineComments(Optional<Boolean> parserCaptureLineComments) {
        this.parserCaptureLineComments = parserCaptureLineComments;
    }

    public void setParserCaptureSourceLocation(Optional<Boolean> parserCaptureSourceLocation) {
        this.parserCaptureSourceLocation = parserCaptureSourceLocation;
    }

    public void setParserMaxTokens(Optional<Integer> parserMaxTokens) {
        this.parserMaxTokens = parserMaxTokens;
    }

    public void setParserMaxWhitespaceTokens(Optional<Integer> parserMaxWhitespaceTokens) {
        this.parserMaxWhitespaceTokens = parserMaxWhitespaceTokens;
    }

    public void setFieldVisibility(String fieldVisibility) {
        this.fieldVisibility = fieldVisibility;
    }

    public void setUnwrapExceptions(Optional<List<String>> unwrapExceptions) {
        this.unwrapExceptions = unwrapExceptions;
    }

    public void setErrorExtensionFields(Optional<List<String>> errorExtensionFields) {
        this.errorExtensionFields = errorExtensionFields;
    }

    public void setHideList(Optional<List<String>> hideList) {
        this.hideList = hideList;
    }

    public void setShowList(Optional<List<String>> showList) {
        this.showList = showList;
    }

    public void setPrintDataFetcherException(Boolean printDataFetcherException) {
        this.printDataFetcherException = printDataFetcherException;
    }

    public void setAllowGet(Boolean allowGet) {
        this.allowGet = allowGet;
    }

    public void setAllowPostWithQueryParameters(Boolean allowPostWithQueryParameters) {
        this.allowPostWithQueryParameters = allowPostWithQueryParameters;
    }

    public void setMetricsEnabled(Boolean metricsEnabled) {
        this.metricsEnabled = metricsEnabled;
    }

    public void setTracingEnabled(Boolean tracingEnabled) {
        this.tracingEnabled = tracingEnabled;
    }

    public void setValidationEnabled(Boolean validationEnabled) {
        this.validationEnabled = validationEnabled;
    }

    public void setEventsEnabled(Boolean eventsEnabled) {
        this.eventsEnabled = eventsEnabled;
    }

    public void setIncludeScalarsInSchema(Boolean includeScalarsInSchema) {
        this.includeScalarsInSchema = includeScalarsInSchema;
    }

    public void setIncludeDirectivesInSchema(Boolean includeDirectivesInSchema) {
        this.includeDirectivesInSchema = includeDirectivesInSchema;
    }

    public void setIncludeSchemaDefinitionInSchema(Boolean includeSchemaDefinitionInSchema) {
        this.includeSchemaDefinitionInSchema = includeSchemaDefinitionInSchema;
    }

    public void setIncludeIntrospectionTypesInSchema(Boolean includeIntrospectionTypesInSchema) {
        this.includeIntrospectionTypesInSchema = includeIntrospectionTypesInSchema;
    }

    public void setQueryComplexityInstrumentation(Optional<Integer> queryComplexityInstrumentation) {
        this.queryComplexityInstrumentation = queryComplexityInstrumentation;
    }

    public void getQueryDepthInstrumentation(Optional<Integer> queryDepthInstrumentation) {
        this.queryDepthInstrumentation = queryDepthInstrumentation;
    }

    private Optional<List<String>> mergeList(Optional<List<String>> currentList, Optional<List<String>> deprecatedList) {

        List<String> combined = new ArrayList<>();
        if (deprecatedList.isPresent()) {
            combined.addAll(deprecatedList.get());
        }
        if (currentList.isPresent()) {
            combined.addAll(currentList.get());
        }

        if (!combined.isEmpty()) {
            return Optional.of(combined);
        } else {
            return Optional.empty();
        }
    }

    private String getStringConfigValue(String key, String defaultValue) {
        return getConfigValue(key, String.class, defaultValue);
    }

    private boolean getBooleanConfigValue(String key) {
        return getBooleanConfigValue(key, false);
    }

    private boolean getBooleanConfigValue(String key, boolean defaultValue) {
        return getConfigValue(key, Boolean.class, defaultValue);
    }
}
