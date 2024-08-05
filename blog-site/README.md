# Blog

A Blogging platform built from the ground up to help users post, comment, and manage their posts all in one place.

## Tech Stack

**Client:** React, Zustand, Formik, TailwindCSS

## Quick start

How to get started and running the project locally

```bash
  npm i #install all npm depencies
```

- Run development server:
  The development server is powered by vite, with HMR(Hot Module Reloading) and by default it running on `http://localhost:5173/`

```bash
 npm run dev
```

- Build for production:

If you are done with development and you want to deploy to production first run a build script to generate static files for deployment

```bash
 npm run build
```

- Preview production build locally:

```bash
 npm run preview
```

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file if you want to include your own configuration for Cloudinary file uploads

`VITE_CLOUDINARY_CLOUD_NAME`

`VITE_CLOUDINARY_UPLOAD_PRESET`
