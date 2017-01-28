import React from 'react';
import { shallow, render, mount } from 'enzyme';
import sinon from 'sinon';
import expect from 'expect';
import EnterTextAnimation from './EnterTextAnimation';
import {it, describe, beforeEach, afterEach} from "mocha";
import {SUBTRACT_CHARACTER_DELAY, APPEND_CHARACTER_DELAY, APPENDING_DONE_DELAY} from './EnterTextAnimation';

let clock;
beforeEach(function () {
    clock = sinon.useFakeTimers();
});

afterEach(function () {
    clock.restore();
});
let SAMPLE_INTRODUCTION_TEXT = ["sample introduction text"];
let animationTime = SAMPLE_INTRODUCTION_TEXT[0].length * APPEND_CHARACTER_DELAY
    + SAMPLE_INTRODUCTION_TEXT[0].length * SUBTRACT_CHARACTER_DELAY + APPENDING_DONE_DELAY;



describe('Component: EnterTextAnimation', () => {
    let SAMPLE_ITRODUCTION_TEXT = ["sample introduction text"];
    it('renders without problems', () => {
        expect(
            shallow(<EnterTextAnimation introductionText={SAMPLE_INTRODUCTION_TEXT}/>).length
        ).toEqual(1);
    });

    it('callback is called after animation ends', () => {
        let callback = sinon.spy();
        shallow(<EnterTextAnimation introductionText={SAMPLE_INTRODUCTION_TEXT} endCallback={callback}/>);

        clock.tick(animationTime + 100);

        expect(callback.called).toEqual(true);
    });

    it('callback is not called before animation ends', () => {
        let callback = sinon.spy();
        shallow(<EnterTextAnimation introductionText={SAMPLE_INTRODUCTION_TEXT} endCallback={callback}/>);

        clock.tick(animationTime - 100);

        expect(callback.called).toEqual(false);
    });


});