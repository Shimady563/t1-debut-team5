//@ts-nocheck
import { useEffect, useRef, useState } from 'react';
import { mockLevels, mockTypes, mockOptions } from '@/globalConsts';
import CustomRadar from '@/libs/CustomRadarLib/CustomRadar';
import { TweenMax } from 'gsap';
import './Radar.scss';
import { useDispatch } from 'react-redux';
import { useActiveTechnologies } from '@/store/TechnologiesStore';
import TechnologiesList from '@/components/TechnologiesList/TechnologiesList';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';

const padding = 0;

const Radar = () => {
  const [options, setOptions] = useState(mockOptions);
  let svgRef = useRef(null);
  const [isExpanded, setIsExpanded] = useState<boolean>(true);
  const [selectedType, setSelectedType] = useState<number>(-1);

  const initialTechs = useActiveTechnologies();

  const [radarDiagram, setRadarDiagram] = useState(
    new CustomRadar(options, {
      elements: initialTechs,
      levels: mockLevels,
      types: mockTypes,
    })
  );

  useEffect(() => {
    setRadarDiagram(
      new CustomRadar(options, {
        elements: initialTechs,
        levels: mockLevels,
        types: mockTypes,
      })
    );
  }, [initialTechs]);

  const rerenderRadar = (seg: number) => {
    if (isExpanded) {
      const segs = Array.isArray(mockTypes)
        ? mockTypes.find((item) => item.slug === seg)
        : null;

      const filteredElements = Array.isArray(initialTechs)
        ? initialTechs.filter(
            (item) => typeof item.type === 'number' && item.type === seg
          )
        : [];

      const newOptions = { ...options };
      newOptions.totalAngle = Math.PI / 2;
      setOptions(newOptions);

      const updatedData = {
        elements: filteredElements,
        levels: mockLevels,
        types: segs ? [segs] : [],
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
    let segment;
    if (svgPoint.x < canvasSize / 2) {
      svgPoint.y < canvasSize / 2 ? (segment = 1) : (segment = 2);
    } else {
      svgPoint.y < canvasSize / 2 ? (segment = 0) : (segment = 3);
    }
    setSelectedType(segment);
    rerenderRadar(segment);
  };

  const expandRadar = () => {
    const updatedData = {
      elements: initialTechs,
      levels: mockLevels,
      types: mockTypes,
    };

    const newOptions = { ...options };
    newOptions.totalAngle = Math.PI * 2;
    setOptions(newOptions);
    setSelectedType(-1);
    setIsExpanded(true);
    setRadarDiagram(new CustomRadar(newOptions, updatedData));
  };

  return (
    <>
      {!isExpanded && (
        <div className="back" onClick={expandRadar}>
          Развернуть радар
        </div>
      )}

      <div className="radar">
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
            {radarDiagram.levelAxes.map((ringAxis: any, id) => (
              <circle
                key={id}
                className="radar__ring"
                cx={radarDiagram.options.baseDimension / 2}
                cy={radarDiagram.options.baseDimension / 2}
                r={ringAxis.j}
                stroke="#aaa"
                strokeWidth={1}
                fill="#fff"
                fillOpacity={0.3}
              ></circle>
            ))}
            {radarDiagram.typeAxes.map((segAxis: any, idx: any) => (
              <g key={idx}>
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
            {radarDiagram.dots.map((dot: any, id) => (
              <g
                key={id}
                className="radar__dot"
                style={{ transform: `translate(${dot.x}px, ${dot.y}px)` }}
              >
                <circle r={10} stroke={'#aaa'} fill={dot.color}></circle>
                <text textAnchor="middle" className="radar__dot__label">
                  {dot.name.substr(0, 15)}
                </text>
              </g>
            ))}
          </svg>
        </div>
        <div className="list">
          <TechnologiesList type={selectedType} />
        </div>
      </div>
    </>
  );
};

export default Radar;
