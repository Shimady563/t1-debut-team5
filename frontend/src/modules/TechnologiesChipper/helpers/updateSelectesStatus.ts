export function updateSelectedStatus(
  originalArr: any[],
  technologiesArr: any[]
): any[] {
  const technologiesDict: Record<number, any> = {};
  technologiesArr.forEach((item) => {
    technologiesDict[item.id] = item;
  });

  return originalArr.map((item) => {
    const techItem = technologiesDict[item.id];
    return techItem
      ? { ...item, selected: true }
      : { ...item, selected: false };
  });
}
