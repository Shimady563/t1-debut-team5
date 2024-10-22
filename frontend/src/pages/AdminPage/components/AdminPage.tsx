import React from 'react';
import './AdminPage.scss';
import TechnologyEditMenu from '@/modules/TechnologyEditMenu/components/TechnologyEditMenu/TechnologyEditMenu';

const AdminPage = () => {
  return (
    <div className="admin-page">
      <TechnologyEditMenu />
    </div>
  );
};

export default AdminPage;
