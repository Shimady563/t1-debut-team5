export function getSelectedIds(
  items: { id: number; selected: boolean }[]
): number[] {
  return items.reduce((acc: number[], item) => {
    if (item.selected) {
      acc.push(item.id);
    }
    return acc;
  }, []);
}
