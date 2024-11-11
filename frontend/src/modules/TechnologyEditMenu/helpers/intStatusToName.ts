import { mockStatuses } from '@/globalConsts';

/**
 * Возращает текстовое название статуса по переданному id
 *
 */
export const statusToName = (id?: number) => {
  const status = mockStatuses.find((el) => el.id == id);
  return status?.label;
};
