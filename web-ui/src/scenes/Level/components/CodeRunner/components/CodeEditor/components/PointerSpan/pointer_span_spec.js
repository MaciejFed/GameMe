import React from 'react';
import { shallow, render, mount } from 'enzyme';
import sinon from 'sinon';
import expect from 'expect';
import PointerSpan from './PointerSpan';
import {INITIAL_DELAY, ANIMATION_DELAY} from './PointerSpan';
import {it, describe, beforeEach, afterEach} from "mocha";

let clock;
beforeEach(function () {
    clock = sinon.useFakeTimers();
});

afterEach(function () {
    clock.restore();
});


describe('Component: PointerSpan', () => {

    it('renders without problems', () => {
        expect(
            shallow(
                <PointerSpan/>
            ).length
        ).toEqual(1);
    });

    it('renders text with pointer', () => {
        const text = 'Sample Text';
        const wrapper = shallow (<PointerSpan text={text}/>);

        expect(wrapper.contains(text)).toEqual(true);
        expect(wrapper.contains('|')).toEqual(true);
    });

    it('starts with initial state', () => {
        const wrapper = shallow (<PointerSpan/>);
        expect(wrapper.state('pointer')).toEqual('|');
        expect(wrapper.state('initialState')).toEqual(true);
    });

    it('changes initial state after initial delay and do not start animation', () => {
        const wrapper = mount(<PointerSpan/>);

        clock.tick(INITIAL_DELAY + 50);

        expect(wrapper.state('pointer')).toEqual('|');
        expect(wrapper.state('initialState')).toEqual(false);
    });

    it('starts to animate after initial delay and animation delay', () => {
        const wrapper = mount(<PointerSpan/>);

        clock.tick(INITIAL_DELAY + ANIMATION_DELAY + 50);

        expect(wrapper.state('pointer')).toEqual('');
        expect(wrapper.state('initialState')).toEqual(false);
    });

    it('makes loop after initial delay a 2 x animation delay', () => {
        const wrapper = mount(<PointerSpan/>);

        clock.tick(INITIAL_DELAY + ANIMATION_DELAY * 2 + 50);

        expect(wrapper.state('pointer')).toEqual('|');
        expect(wrapper.state('initialState')).toEqual(false);
    });
});