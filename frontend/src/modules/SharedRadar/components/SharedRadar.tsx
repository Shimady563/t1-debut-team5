//@ts-nocheck
import { useEffect, useRef, useState } from 'react';
import { mockLevels, mockTypes, mockOptions } from '@/globalConsts';
import CustomRadar from '@/libs/CustomRadarLib/CustomRadar';
import './SharedRadar.scss';
import { useActiveTechnologies } from '@/store/TechnologiesStore';
import TechnologiesList from '@/components/TechnologiesList/TechnologiesList';
import useGetAllTechnologiesRequest from '@/globalApi/getAllTechnologiesRequest';
const RADAR_PADDING = 0;

const SharedRadar = () => {
  const [options, setOptions] = useState(mockOptions);
  let svgRef = useRef(null);
  const [initialTechs, setInitialTechs] = useState([]);

  useEffect(() => {
    const handleMessage = (event: MessageEvent) => {
      if (event.data && event.data.type === 'setData') {
        console.log(event.data.data);
        setInitialTechs((prevState) => [...prevState, ...event.data.data]);
      }
    };

    window.addEventListener('message', handleMessage);
    return () => window.removeEventListener('message', handleMessage);
  }, []);

  useEffect(() => {
    console.log(initialTechs);
    setRadarDiagram(
      new CustomRadar(options, {
        elements: initialTechs,
        levels: mockLevels,
        types: mockTypes,
      })
    );
  }, [initialTechs, options, mockLevels, mockTypes]);

  const [radarDiagram, setRadarDiagram] = useState(
    new CustomRadar(options, {
      elements: initialTechs,
      levels: mockLevels,
      types: mockTypes,
    })
  );

  return (
    <>
      <div className="radar">
        <div className="radar-container">
          <svg
            onClick={(e: MouseEvent) => {
              radarClickHandler(e);
            }}
            id="radar-plot"
            viewBox={`${-RADAR_PADDING} ${-RADAR_PADDING} ${
              radarDiagram.options.baseDimension + 2 * RADAR_PADDING
            } ${radarDiagram.options.baseDimension + 2 * RADAR_PADDING}`}
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
                {dot.moved === 0 ? (
                  <circle r={10} stroke={'#aaa'} fill={dot.color}></circle>
                ) : dot.moved === 1 ? (
                  <polygon
                    stroke={'#aaa'}
                    points="-10,10 10,10 0,-7.32"
                    fill={dot.color}
                  />
                ) : (
                  <polygon
                    stroke={'#aaa'}
                    points="-10,-10 10,-10 0,7.32"
                    fill={dot.color}
                  />
                )}

                <text textAnchor="middle" className="radar__dot__label">
                  {dot.name.substr(0, 15)}
                </text>
              </g>
            ))}
          </svg>
        </div>
        <div className="list">
          <TechnologiesList
            isTechsPassed={true}
            passedTechs={initialTechs}
            type={-1}
          />
        </div>
      </div>
    </>
  );
};

export default SharedRadar;
