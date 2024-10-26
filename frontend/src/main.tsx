import { createRoot } from 'react-dom/client';
import App from '@App/App';
import './index.scss';
import { Provider } from 'react-redux';
import store from './store/store';

import { ThemeProvider } from 'styled-components';
import { LIGHT_THEME } from '@admiral-ds/react-ui';

createRoot(document.getElementById('root')!).render(
  <ThemeProvider theme={LIGHT_THEME}>
    <Provider store={store}>
      <App />
    </Provider>
  </ThemeProvider>
);
