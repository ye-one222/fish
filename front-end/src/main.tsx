import './tailwind.css';

import React from 'react';
import ReactDOM from 'react-dom/client'

import { RootLayout } from './app/layout';
import { FishRouter } from './router/index'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RootLayout>
      <FishRouter>
        
      </FishRouter>
    </RootLayout>
  </React.StrictMode>
)