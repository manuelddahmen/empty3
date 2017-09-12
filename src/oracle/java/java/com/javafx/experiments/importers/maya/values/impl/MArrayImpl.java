/*
 * Copyright (c) 2017. Tous les fichiers dans ce programme sont soumis à la License Publique Générale GNU créée par la Free Softxware Association, Boston.
 * La plupart des licenses de parties tièrces sont compatibles avec la license principale.
 * Les parties tierces peuvent être soumises à d'autres licenses.
 * Montemedia : Creative Commons
 * ECT : Tests à valeur artistique ou technique.
 * La partie RayTacer a été honteusement copiée sur le Net. Puis traduite en Java et améliorée.
 * Java est une marque de la société Oracle.
 *
 * Pour le moment le programme est entièrement accessible sans frais supplémentaire. Get the sources, build it, use it, like it, share it.
 */

package com.javafx.experiments.importers.maya.values.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.javafx.experiments.importers.maya.MayaImporter;
import com.javafx.experiments.importers.maya.types.MArrayType;
import com.javafx.experiments.importers.maya.values.MArray;
import com.javafx.experiments.importers.maya.values.MData;

public class MArrayImpl extends MDataImpl implements MArray {
    public static final boolean DEBUG = MayaImporter.DEBUG;
    public static final boolean WARN = MayaImporter.WARN;

    List<MData> data = new ArrayList();

    static class Parser {
        private MArray array;

        Parser(MArray array) {
            this.array = array;
        }

        public void parse(Iterator<String> values) {
            int i = 0;
            while (values.hasNext()) {
                array.setSize(i + 1);
                //            System.out.println("get " + i +" of " + array.getSize());
                array.getData(i).parse(values);
                i++;
            }
        }
    }

    class MArraySlice extends MDataImpl implements MArray {
        private MArray array;
        private int base;
        private int length;

        MArraySlice(
                MArray array,
                int base,
                int length) {
            super((MArrayType) array.getType());
            this.array = array;
            this.base = base;
            this.length = length;
        }

        public void setSize(int size) {
            array.setSize(base + size);
        }

        public int getSize() {
            return length;
        }

        public void set(int index, MData data) {
            if (index >= length) {
                throw new ArrayIndexOutOfBoundsException(index);
            }
            array.set(base + index, data);
        }

        public MData getData(int index) {
            return array.getData(base + index);
        }

        public MData getData(int start, int end) {
            return new MArraySlice(this, start, end - start);
        }

        public List<MData> get() {
            // FIXME
            throw new RuntimeException("Probably shouldn't fetch the data behind a slice");
        }

        public void parse(Iterator<String> values) {
            new Parser(this).parse(values);
        }
    }

    public MArrayImpl(MArrayType type) {
        super(type);
    }

    public MArrayType getArrayType() {
        return (MArrayType) getType();
    }

    public List<MData> get() {
        return data;
    }

    public MData getData(int index) {
        if (index >= data.size()) {  // TODO huge hack, to prevent out of bounds exception
            int oldIndex = index;
            index = data.size() - 1;
            if (WARN) System.err.println("Changed index from [" + oldIndex + "] to [" + index + "]");
        }
        return data.get(index);
    }

    public MData getData(int start, int end) {
        return new MArraySlice(this, start, end - start);
    }

    public void set(int index, MData data) {
        this.data.set(index, data);
    }

    public void setSize(int size) {
        while (data.size() < size) {
            data.add(getArrayType().getElementType().createData());
        }
        //        System.out.println("SET SIZE: " + size + " data.size="+data.size());
    }

    public int getSize() {
        return data.size();
    }

    public void parse(Iterator<String> values) {
        new Parser(this).parse(values);
    }

    public String toString() {
        return data.toString();
    }
}
