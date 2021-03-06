/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2017 The TestFX Contributors
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence"); You may
 * not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */
package org.testfx.cases.acceptance;

import java.util.function.Predicate;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.testfx.TestFXRule;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;

@Ignore
public class SceneRootAssertionTest {

    @Rule
    public TestFXRule testFXRule = new TestFXRule();
    StackPane stackPane;
    Label label;

    @Before
    public void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupSceneRoot(() -> {
            label = new Label("foobar");
            stackPane = new StackPane(label);
            return stackPane;
        });
        FxToolkit.showStage();
    }

    @Test
    public void should_have_stage_root_with_label() {
        FxAssert.verifyThat(stackPane, hasChild(label));
    }

    private Predicate<Parent> hasChild(Node node) {
        return (Parent parent) -> parent.getChildrenUnmodifiable().contains(node);
    }

}
