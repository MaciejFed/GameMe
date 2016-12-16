import React from 'react';
import { shallow, render, mount } from 'enzyme';
import expect from 'expect';
import CodeRunner from '../src/components/code-runner';
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

    it('focus on input when click on div', () => {
        const wrapper = mount(<CodeRunner />);
        let inputContainer = wrapper.find('div.code-container');

        inputContainer.simulate('click');

        expect(inputContainer.find('input').node).toEqual(document.activeElement);
    });

    it('changes text when input on div');
});