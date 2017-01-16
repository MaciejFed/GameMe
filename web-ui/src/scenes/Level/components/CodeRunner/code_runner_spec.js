import React from 'react';
import { shallow, render, mount } from 'enzyme';
import expect from 'expect';
import CodeRunner from './CodeRunner';
import jsdom from 'jsdom'
const doc = jsdom.jsdom('<!doctype html><html><body></body></html>');
global.document = doc;
global.window = doc.defaultView;

describe('Component: CodeRunner', () => {

    it('renders without problems', () => {
        expect(
            shallow(
                <CodeRunner/>
            ).length
        ).toEqual(1);
    });

    it('focus on input when click event on div', () => {
        const wrapper = mount(<CodeRunner />);
        let inputContainer = wrapper.find('#code-container');

        inputContainer.simulate('click');

        expect(inputContainer.find('input').node).toEqual(document.activeElement);
    });

    it('changes text when input on div');
    it('saves code on successful compile response');
    it('display saved code after click on saved one');
    it('changes style when focus on input');
    it('focuses on div when any keyboard input');
    it('shows progress animation when waiting for compile response')
});