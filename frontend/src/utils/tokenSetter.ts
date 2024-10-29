export const setTokenToCookie = (token: string) => {
  document.cookie = `jwt=${token}; path=/; SameSite=Lax; Expires=${new Date(
    Date.now() + 365 * 24 * 60 * 60 * 1000
  ).toUTCString()}`;
};
