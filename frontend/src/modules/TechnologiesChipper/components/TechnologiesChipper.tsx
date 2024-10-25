import React from 'react';
import './TechnologiesChipper.scss';
import { Chips } from '@admiral-ds/react-ui';
import type { ChipsProps } from '@admiral-ds/react-ui';

import { mockElements } from '@/modules/Radar/consts';

// function deepClone(obj: any): any {
//   if (typeof obj !== 'object' || obj === null) {
//     return obj;
//   }

//   if (Array.isArray(obj)) {
//     return obj.map(deepClone);
//   }

//   return Object.fromEntries(
//     Object.entries(obj).map(([key, value]) => [key, deepClone(value)])
//   );
// }

// const copiedMockElements = deepClone(mockElements);
// copiedMockElements.forEach((element) => {
//   element.selected = false;
// });

// console.log(copiedMockElements);

const TechnologiesChipper = () => {
  //   const [listM, setListM] = React.useState<any>(copiedMockElements);
  //   const handleKeyM = (id: number) => {
  //     setListM((prev) =>
  //       prev.map((item) =>
  //         item.id === id ? { ...item, selected: !item.selected } : { ...item }
  //       )
  //     );
  //   };
  //   const req = () => {
  //     console.log(listM);
  //   };
  //   return (
  //     <div className="chipper">
  //       <div className="chipper__content">
  //         {listM.map((el: any) => (
  //           <Chips
  //             key={el.id}
  //             className="chipper_el"
  //             onClick={handleKeyM.bind(null, el.id)}
  //             selected={el.selected}
  //           >
  //             <div className="chipper__content_item">{el.name}</div>
  //           </Chips>
  //         ))}
  //       </div>
  //       <div className="" onClick={req}>
  //         aaaa
  //       </div>
  //     </div>
  //   );
};

export default TechnologiesChipper;
