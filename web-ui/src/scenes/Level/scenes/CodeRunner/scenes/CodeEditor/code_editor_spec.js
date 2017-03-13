import React from 'react';
import expect from 'expect';
import * as actions from './codeEditorActions';
import CodeEditor from './CodeEditor'
import store  from './../../../../../../store'
import { Provider } from 'react-redux'
import { shallow } from "enzyme";

describe('Component: CodeEditor', () => {

    it('Renders Without Problems', () => {
        expect(shallow(
            <Provider store={store}>
                <CodeEditor />
            </Provider>
            ).length
        ).toEqual(1)

    });
});