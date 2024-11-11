export const throwValidationError = (place: HTMLElement, errorDesc: string) => {
  place.innerHTML = errorDesc;
};

export const removeValidationError = (place: HTMLElement) => {
  place.innerHTML = '';
};
