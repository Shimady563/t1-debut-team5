/**
 * Получение jwt-токена из cookie
 */
export const getTokenFromCookie = (): string | null => {
  const token = document.cookie
    .split('; ')
    .find((row) => row.startsWith('jwt='))
    ?.split('=')[1];
  return token ? token : null;
};
