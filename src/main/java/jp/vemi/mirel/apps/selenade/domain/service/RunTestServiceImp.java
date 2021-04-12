/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.domain.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selectors.ByText;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By.ByXPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.ConstructorException;

import jp.vemi.extension.function_resolver.api.ApiResolver;
import jp.vemi.extension.function_resolver.api.ApiResolverCondition;
import jp.vemi.extension.function_resolver.dto.Api;
import jp.vemi.framework.exeption.MirelApplicationException;
import jp.vemi.framework.exeption.MirelSystemException;
import jp.vemi.framework.util.FileUtil;
import jp.vemi.framework.util.StorageUtil;
import jp.vemi.mirel.apps.selenade.agent.SelenideSuite;
import jp.vemi.mirel.apps.selenade.domain.dto.RunTestParameter;
import jp.vemi.mirel.apps.selenade.domain.dto.RunTestResult;
import jp.vemi.mirel.apps.selenade.dto.ArTestRun;
import jp.vemi.mirel.apps.selenade.dto.yml.ArUsecase;
import jp.vemi.mirel.apps.selenade.dto.yml.ArScenario;
import jp.vemi.mirel.apps.selenade.dto.yml.ArSelenadePage;
import jp.vemi.mirel.foundation.abst.dao.repository.FileManagementRepository;
import jp.vemi.mirel.foundation.web.api.dto.ApiRequest;
import jp.vemi.mirel.foundation.web.api.dto.ApiResponse;

import static com.codeborne.selenide.Selenide.*;

/**
 * {@link RunTestService テスト実行} の具象です。
 */
@Service
@Transactional
@Generated(value = {"jp.vemi.ste.domain.engine.TemplateEngineProcessor"},
    comments = "Generated from /mirel/service:191207A")
public class RunTestServiceImp implements RunTestService {

    /** {@link FileManagementRepository} */
    @Autowired
    protected FileManagementRepository fileManagementRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponse<RunTestResult> invoke(ApiRequest<RunTestParameter> parameter) {

        ApiResponse<RunTestResult> resp = ApiResponse.<RunTestResult>builder().build();
        resp.setModel(RunTestResult.builder().build());
        exec(parameter, resp);
        return resp;

    }

    protected void exec(ApiRequest<RunTestParameter> parameter, ApiResponse<RunTestResult> resp) {

        String appKey = (String)parameter.getModel().params.get(0).get("key");
        ArTestRun testRun = new ArTestRun();
        List<File> files = FileUtil.getFiles(StorageUtil.getFile("apps/apprunner/defs/" + appKey));
        for (File file : files) {
            if (false == file.isDirectory()) {
                continue;
            }

            String fileName = file.getName();

            // Usecase.
            if (fileName.startsWith("usecase") && isYaml(fileName))  {
                ArUsecase usecase = getYaml(file, ArUsecase.class);
                testRun.addUsecase(usecase);
                continue;
            }

            // Page.
            if (fileName.endsWith("page") && isYaml(fileName)) {
                ArSelenadePage page = getYaml(file, ArSelenadePage.class);
                testRun.addPage(page);
                continue;
            }

            // Scenario.
            if (fileName.startsWith("scenario") && isYaml(fileName)) {
                ArScenario scenario = getYaml(file, ArScenario.class);
                testRun.addScenario(scenario);
            }
        }

        SelenideConfig config = new SelenideConfig();
        SelenideDriver driver = new SelenideDriver(config);

        for (Map.Entry<String,ArScenario> entry : testRun.getScenarios().entrySet()) {
            ArScenario scenario = entry.getValue();
            for (Map.Entry<String, ArUsecase> en2ry : scenario.getUsecases().entrySet()) {
                ArUsecase defaultUsecase = testRun.getUsecases().get(en2ry.getKey());
                ArUsecase mergedUsecase = mergeUsecase(en2ry.getValue(), defaultUsecase);

                List<ArUsecase.Operation> operations = mergedUsecase.getOperations();
                for (ArUsecase.Operation operation : operations) {
                    operation.getPageId();
                    ArSelenadePage page = testRun.getPages().get(operation.getPageId());

                    for (ArSelenadePage.Action action : page.getActions()) {
                        String locator = action.getLocator();
                        ApiResolverCondition condition = ApiResolverCondition.of(locator);
                        Api api = (Api)ApiResolver.getInstance().resolve(condition);
                        SelenideElement sement = null;
                        switch(api.getApiName()) {
                            case "open":
                                driver.open("param");
                                break;
                            case "xpath":
                                sement = driver.$x((String)api.getParameter().get("value"));
                                break;
                            default:
                                break;
                        }

                        if (isAction("type")) { // TODO fix type condition
                            if (null == sement) {
                                // error
                            } else {
                                // input or click.
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    private Boolean isAction(String type) {
        return true; // TODO
    }
    protected ArUsecase mergeUsecase(ArUsecase usecase, ArUsecase defaultUsecase) {
        // TODO マージ実装
        return usecase;
    }


    /**
     * Get object from yaml file.<br/>
     * @param <T>
     * @param file
     * @param clazz
     * @return
     */
    protected <T> T getYaml(File file, Class<T> clazz) {
        T object;
        try(InputStream stream = new FileSystemResource(file).getInputStream()) {
            object = new Yaml(new Constructor(clazz)).loadAs(stream, clazz);
        } catch (final ConstructorException e) {
            e.printStackTrace();
            String msg = "yamlの読込でエラーが発生しました。";
            if(isDebugMode()) {
                msg += "debug log:" + e.getLocalizedMessage();
            }
            throw new MirelApplicationException(msg, e);
        } catch (final IOException e) {
            e.printStackTrace();
            throw new MirelSystemException("yamlの読込で入出力エラーが発生しました。", e);
        }
        return object;
    }
    protected boolean isDebugMode() {
        return true;
    }

    private static Boolean isYaml(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }

        if (fileName.endsWith(".yml")) {
            return true;
        }

        if (fileName.endsWith(".yaml")) {
            return true;
        }

        return false;
    }
}
