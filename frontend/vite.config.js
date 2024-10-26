import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';
export default defineConfig({
    resolve: {
        alias: {
            '@': path.resolve(__dirname, './src'),
            '@assets': path.resolve(__dirname, './src/assets'),
            '@components': path.resolve(__dirname, './src/components'),
            '@pages': path.resolve(__dirname, './src/pages'),
            '@modules': path.resolve(__dirname, './src/modules'),
            '@styles': path.resolve(__dirname, './src/styles'),
            '@App': path.resolve(__dirname, './src/App'),
            '@libs': path.resolve(__dirname, './src/libs'),
            '@utils': path.resolve(__dirname, './src/utils'),
        },
    },
    plugins: [react()],
});
