// @ts-nocheck
import React, { useEffect, useRef, useState } from 'react';
import {
  mockTechnologies,
  mockRings,
  mockElements,
  mockSegments,
  mockOptions,
} from '@modules/Radar/consts';
import CustomRadar from '@/libs/CustomRadarLib/CustomRadar';
import { TweenMax } from 'gsap';
import './Radar.scss';

const padding = 0;

const Radar = () => {
  let svgRef = useRef(null);

  const [options, setOptions] = useState(mockOptions);
  const [elements, setElements] = useState(mockElements);
  const [segments, setSegments] = useState(mockSegments);

  const [isExpanded, setIsExpanded] = useState(true);

  const [radarDiagram, setRadarDiagram] = useState(
    new CustomRadar(options, {
      elements,
      rings: mockRings,
      segments,
    })
  );

  const rerenderRadar = (seg: number) => {
    if (isExpanded) {
      const segs = Array.isArray(segments)
        ? segments.find((item) => item.slug === seg)
        : null;

      const filteredElements = Array.isArray(elements)
        ? elements.filter(
            (item) => typeof item.segment === 'number' && item.segment === seg
          )
        : [];

      const newOptions = { ...options };
      newOptions.totalAngle = Math.PI / 2;
      setOptions(newOptions);

      const updatedData = {
        elements: filteredElements,
        rings: mockRings,
        segments: segs ? [segs] : [],
      };
      setIsExpanded(false);
      setRadarDiagram(new CustomRadar(newOptions, updatedData));
    }
  };

  useEffect(() => {
    if (options) {
      let vb;
      switch (options.totalAngle) {
        case Math.PI:
          vb = `${-padding} ${-padding} ${
            radarDiagram.options.baseDimension + 2 * padding
          } ${radarDiagram.options.baseDimension / 2 + padding}`;
          break;
        case Math.PI * 2:
          vb = `${-padding} ${-padding} ${
            radarDiagram.options.baseDimension + 2 * padding
          } ${radarDiagram.options.baseDimension + 2 * padding}`;
          break;
        case Math.PI / 2:
          vb = `${radarDiagram.options.baseDimension / 2} ${-padding} ${
            (radarDiagram.options.baseDimension + 2 * padding) / 2
          } ${(radarDiagram.options.baseDimension + 2 * padding) / 2}`;
          break;
        default:
          break;
      }
      TweenMax.to(svgRef, 1, { attr: { viewBox: vb } });
    }
  }, [options]);

  const radarClickHandler = (e: MouseEvent) => {
    const clientX = e.clientX;
    const clientY = e.clientY;

    const svgElement = e.currentTarget as SVGSVGElement;

    const point = new DOMPoint(clientX, clientY);

    const svgPoint = point.matrixTransform(
      svgElement.getScreenCTM()?.inverse() || new DOMMatrix()
    );

    const canvasSize = svgElement.width.animVal.value;
    console.log(canvasSize, svgPoint.x, svgPoint.y);
    let segment;
    if (svgPoint.x < canvasSize / 2) {
      svgPoint.y < canvasSize / 2 ? (segment = 2) : (segment = 3);
    } else {
      svgPoint.y < canvasSize / 2 ? (segment = 1) : (segment = 4);
    }

    rerenderRadar(segment);
  };

  return (
    <div className="radar-container">
      <svg
        onClick={(e: MouseEvent) => {
          radarClickHandler(e);
        }}
        id="radar-plot"
        viewBox={`${-padding} ${-padding} ${
          radarDiagram.options.baseDimension + 2 * padding
        } ${radarDiagram.options.baseDimension + 2 * padding}`}
        xmlns="http://www.w3.org/2000/svg"
        ref={(el) => (svgRef = el)}
      >
        <circle
          r={radarDiagram.options.baseDimension / 2}
          cx={radarDiagram.options.baseDimension / 2}
          cy={radarDiagram.options.baseDimension / 2}
          fill="rgb(181, 191, 255)"
        ></circle>
        {radarDiagram.ringAxes.map((ringAxis: any) => (
          <circle
            className="radar__ring"
            key={ringAxis.slug}
            cx={radarDiagram.options.baseDimension / 2}
            cy={radarDiagram.options.baseDimension / 2}
            r={ringAxis.j}
            stroke="#aaa"
            strokeWidth={1}
            fill="#fff"
            fillOpacity={0.3}
          ></circle>
        ))}
        {radarDiagram.segmentAxes.map((segAxis: any, idx: any) => (
          <g key={segAxis.slug}>
            <line
              className="radar__segment-axis"
              x1={segAxis.axis.x1}
              x2={segAxis.axis.x2}
              y1={segAxis.axis.y1}
              y2={segAxis.axis.y2}
              stroke={'#aaa'}
              strokeWidth={1}
            ></line>
          </g>
        ))}
        {radarDiagram.dots.map((dot: any) => (
          <g
            key={dot.label}
            className="radar__dot"
            style={{ transform: `translate(${dot.x}px, ${dot.y}px)` }}
          >
            <circle r={10} stroke={'#aaa'} fill={dot.color}></circle>
            <text textAnchor="middle" className="radar__dot__label">
              {dot.label.substr(0, 15)}
            </text>
          </g>
        ))}
      </svg>
    </div>
  );
};

export default Radar;
