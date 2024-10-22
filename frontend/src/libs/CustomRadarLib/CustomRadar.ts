// @ts-nocheck
class CustomRadar {
  constructor(options, { elements, levels, types }) {
    this.levels = levels;
    this.elements = Array.isArray(elements) ? elements : [elements];
    this.types = Array.isArray(types) ? types : [types];

    // Extend options
    const defaultOptions = {
      totalAngle: Math.PI * 2,
      baseDimension: 1000,
      padding: 0,
      minPlotRadius: 0,
    };

    this.options = { ...defaultOptions, ...options };
    this.maxPlotRadius = this.options.baseDimension / 2 - this.options.padding;

    // Calculate metadata
    this.metaData = this.getMetaData();

    // Calculate Axes placement
    this.typeAxes = this.getTypeAxes();
    this.levelAxes = this.getlevelAxes();

    // Calculate dot placement
    this.dots = this.getDots();
  }

  getTypeLabelPathBase() {
    const startCoord = this.polarToCartesian(this.maxPlotRadius, 0);
    const endCoord = this.polarToCartesian(
      this.maxPlotRadius,
      this.options.totalAngle / this.types.length
    );
    return this.getTypeLabelPathD(startCoord, endCoord);
  }

  getTypeAxes() {
    return this.types?.map((seg, idx) => {
      const i = (idx * this.options.totalAngle) / this.types.length;
      const upperCoord = this.polarToCartesian(this.maxPlotRadius, i);
      const j = ((idx + 1) * this.options.totalAngle) / this.types.length;
      const upperCoordJ = this.polarToCartesian(this.maxPlotRadius, j);
      return {
        ...seg,
        i,
        j,
        axis: {
          x1: this.options.baseDimension / 2,
          y1: this.options.baseDimension / 2,
          x2: upperCoord.x,
          y2: upperCoord.y,
          labelPath: this.getTypeLabelPathD(upperCoord, upperCoordJ),
        },
      };
    });
  }

  getTypeLabelPathD(startCoord, endCoord) {
    return `M ${endCoord.x},${endCoord.y} A${this.maxPlotRadius},${this.maxPlotRadius} 0 0,1 ${startCoord.x},${startCoord.y}`;
  }

  getlevelAxes() {
    return this.levels.map((level, idx) => {
      // start
      const i =
        this.options.minPlotRadius +
        (idx * (this.maxPlotRadius - this.options.minPlotRadius)) /
          this.levels.length;
      const j =
        this.options.minPlotRadius +
        ((idx + 1) * (this.maxPlotRadius - this.options.minPlotRadius)) /
          this.levels.length;
      return {
        ...level,
        i,
        j,
      };
    });
  }

  getMetaData() {
    const typeCounts = {};
    const levelCounts = {};
    const countMatrix = {};
    if (!this.elements || !Array.isArray(this.elements)) {
      return { typeCounts, levelCounts, countMatrix };
    }

    this.elements.forEach((element) => {
      if (typeof element.type === 'number') {
        typeCounts[element.type] = (typeCounts[element.type] || 0) + 1;
      }
      if (typeof element.level === 'number') {
        levelCounts[element.level] = (levelCounts[element.level] || 0) + 1;
      }
      const slice = `${element.type || 0}.${element.level || 0}`;
      countMatrix[slice] = (countMatrix[slice] || 0) + 1;
    });

    return { typeCounts, levelCounts, countMatrix };
  }

  getDots() {
    const idx = {};

    return this.elements.map((el) => {
      const level = this.levelAxes.filter((r) => r.slug === el.level)[0];
      const type = this.typeAxes.filter((s) => s.slug === el.type)[0];
      const slice = `${el.type}.${el.level}`;
      idx[slice] = idx[slice] ? idx[slice] + 1 : 1;
      const radialCoord = this.layoutDot(
        level,
        type,
        idx[slice],
        this.metaData.countMatrix[slice]
      );

      const coords = this.polarToCartesian(radialCoord.r, radialCoord.angle);
      return { ...el, ...coords, r: 5, color: type.color };
    });
  }

  /**
   *
   * @param {level} level level Entry
   * @param {type}type Entry
   * @param {Number} idx Dot's index in the slice / cell
   * @param {Number} total Total dots in the slice / cell
   * @returns
   */
  layoutDot(level, type, idx, total) {
    const elemPerRow = Math.floor(level.i / 30) + 2;
    // relIdx -> relative index
    // rel index repeats from 1 for every row after max per row reaches
    const relIdx = ((idx - 1) % elemPerRow) + 1;
    const relIdxlevel = Math.ceil(idx / elemPerRow);

    // Number of rows in the cell (Slice)
    const levelRows = Math.ceil(total / elemPerRow);

    // relTotal total per row  == elemPerRow or total if total below max elemPerRow
    const relTotal = Math.min(total + 1, elemPerRow + 1);

    // Origin------Bound.i------idx1--------idx2--------Bound.j [Section formula]
    return {
      r: ((level.j - level.i) * relIdxlevel) / (levelRows + 1) + level.i,
      angle: ((type.j - type.i) * relIdx) / relTotal + type.i,
    };
  }

  cartesianToPolar(x, y) {
    return {
      r: Math.sqrt(Math.pow(x, 2), Math.pow(y, 2)),
      angle: Math.atan(y / x),
    };
  }

  /**
   * Polar to Cartesian conversion
   * @param {Number} r Radius / Distance from origin
   * @param {Number} angle Angle in radian
   * @returns
   */
  polarToCartesian(r, angle) {
    // translate origin to center -> (+ this.options.baseDimension / 2)
    //  y -ve because css/svg y is inverse of normal geometry
    return {
      x: r * Math.cos(angle) + this.options.baseDimension / 2,
      y: -r * Math.sin(angle) + this.options.baseDimension / 2,
    };
  }
}

export default CustomRadar;
