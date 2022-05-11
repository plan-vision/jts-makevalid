/**
 * H2GIS is a library that brings spatial support to the H2 Database Engine
 * <http://www.h2database.com>. H2GIS is developed by CNRS
 * <http://www.cnrs.fr/>.
 *
 * This code is part of the H2GIS project. H2GIS is free software; 
 * you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation;
 * version 3.0 of the License.
 *
 * H2GIS is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details <http://www.gnu.org/licenses/>.
 *
 *
 * For more information, please consult: <http://www.h2gis.org/>
 * or contact directly: info_at_h2gis.org
 */
package org.h2gis.functions.spatial.clean;

import org.locationtech.jts.geom.Geometry;

import java.sql.SQLException;

/**
 * Function to make a geometry valid.
 * @author Michaël Michaud
 * @author Erwan Bocher, CNRS
 */
public class ST_MakeValid {
    
    public static Geometry validGeom(Geometry geometry) {
        return validGeom(geometry, true, true, true);
    }
    
    public static Geometry validGeom(Geometry geometry, boolean preserveGeomDim) {
        return validGeom(geometry, preserveGeomDim, true, true);
    }
    
    public static Geometry validGeom(Geometry geometry, boolean preserveGeomDim,  boolean preserveDuplicateCoord) {
        return validGeom(geometry, preserveGeomDim, preserveDuplicateCoord, true);
    }
    
    /**
     *
     * @param geometry
     * @param preserveGeomDim
     * @param preserveDuplicateCoord
     * @param preserveCoordDim
     * @return
     * @throws SQLException
     */
    public static Geometry validGeom(Geometry geometry, boolean preserveGeomDim, boolean preserveDuplicateCoord, boolean preserveCoordDim) {
        if (geometry == null) {
            return null;
        }
        if (geometry.isEmpty()) {
            return geometry;
        }

        MakeValidOp op = new MakeValidOp();
        op.setPreserveGeomDim(preserveGeomDim);
        op.setPreserveDuplicateCoord(preserveDuplicateCoord);
        op.setPreserveCoordDim(preserveCoordDim);
        return op.makeValid(geometry);
    }
}